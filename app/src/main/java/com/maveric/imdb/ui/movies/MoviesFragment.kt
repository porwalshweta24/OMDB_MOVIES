package com.maveric.imdb.ui.movies

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.maveric.imdb.R
import com.maveric.imdb.databinding.FragmentMoviesBinding
import com.maveric.imdb.ui.movies.helper.MoviesAdapter
import com.maveric.imdb.ui.base.MvvmFragment
import com.maveric.imdb.ui.bottom.BottomFragmentDirections.Companion.actionBottomToMovieDetails
import com.maveric.imdb.utils.extensions.string
import com.maveric.imdb.utils.field.NonNullObserver
import com.maveric.imdb.utils.helper.PaginationScrollListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : MvvmFragment<FragmentMoviesBinding, MoviesViewModel>(
    R.layout.fragment_movies,
    MoviesViewModel::class.java
) {
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = super.onCreateView(inflater, container, savedInstanceState)?.apply {
        setHasOptionsMenu(true)
    }

    override fun onEveryInitialization(savedBundle: Bundle?) {
        actionBar?.setTitle(R.string.main_menu_movies)

        with(data) {
            movies.observeNonNull(viewLifecycleOwner, NonNullObserver {
                moviesAdapter.updateCustom(it)
            })

            loading.observeNonNull(viewLifecycleOwner, NonNullObserver {
                moviesAdapter.updateLoading(it)
            })

            eventError.observeEvent(viewLifecycleOwner, NonNullObserver {
                val message = it.message ?: string(R.string.error_message_unknown)
                Snackbar.make(moviesRecycler, message, LENGTH_LONG).show()
            })

            eventClicked.observeEvent(viewLifecycleOwner, NonNullObserver {
                mainNavigation?.navigate(actionBottomToMovieDetails(it.imdbId))
            })
        }

        moviesAdapter = MoviesAdapter(data)

        val gridLayoutManager = GridLayoutManager(activity,2)

        moviesRecycler?.apply {
            layoutManager = gridLayoutManager
            itemAnimator = null
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

        moviesRecycler?.addOnScrollListener(
            object : PaginationScrollListener(gridLayoutManager) {
                override fun onListBottom() = data.loadMovies()
                override fun isLastPage(): Boolean = data.isLimitReached
                override fun isLoading(): Boolean = data.loading.value
            }
        )
    }

    override fun onFirstInitialization() {
        data.initialize()
    }

    override fun onDestroyView() {
        moviesRecycler.adapter = null
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)

        val item = menu.findItem(R.id.menu_toolbar_search)
        val view = item.actionView as SearchView

        view.maxWidth = Int.MAX_VALUE

        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                data.search(query)
                view.apply { if (!isIconified) isIconified = true }
                item.collapseActionView()
                return false
            }

            override fun onQueryTextChange(query: String): Boolean = false
        })
    }
}