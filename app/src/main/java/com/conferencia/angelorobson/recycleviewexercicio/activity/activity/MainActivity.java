package com.conferencia.angelorobson.recycleviewexercicio.activity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.conferencia.angelorobson.recycleviewexercicio.R;
import com.conferencia.angelorobson.recycleviewexercicio.activity.adapter.AdapterMovies;
import com.conferencia.angelorobson.recycleviewexercicio.activity.listener.EndlessRecyclerOnScrollListener;
import com.conferencia.angelorobson.recycleviewexercicio.activity.model.Movie;
import com.conferencia.angelorobson.recycleviewexercicio.activity.model.MovieResults;
import com.conferencia.angelorobson.recycleviewexercicio.activity.service.MovieService;
import com.conferencia.angelorobson.recycleviewexercicio.activity.service.ServiceGenerator;
import com.conferencia.angelorobson.recycleviewexercicio.activity.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.conferencia.angelorobson.recycleviewexercicio.activity.constants.Constants.API_KEY;
import static com.conferencia.angelorobson.recycleviewexercicio.activity.constants.Constants.INITIAL_PAGE;
import static com.conferencia.angelorobson.recycleviewexercicio.activity.constants.Constants.LANGUAGE;
import static com.conferencia.angelorobson.recycleviewexercicio.activity.constants.Constants.POPULARITY_DESC;

public class MainActivity extends AppCompatActivity {

  RecyclerView mRecyclerView;
  RecyclerView.LayoutManager mLayoutManager;
  AdapterMovies adapterMovies;
  MovieService mMovieService;
  List<Movie> movies = new ArrayList<>();
  private ProgressBar mProgressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initViews();
  }

  private void initViews(){
    mRecyclerView = findViewById(R.id.recyclerView);

    adapterMovies = new AdapterMovies(movies);

    mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    mRecyclerView.setAdapter(adapterMovies);

    mRecyclerView.addOnItemTouchListener(
      new RecyclerItemClickListener(
        getApplicationContext(),
        mRecyclerView,
        new RecyclerItemClickListener.OnItemClickListener() {
          @Override
          public void onItemClick(View view, int position) {
            Toast.makeText(getApplicationContext(), movies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onLongItemClick(View view, int position) {
            Toast.makeText(getApplicationContext(), movies.get(position).getDescription(), Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

          }
        }
      )

    );

    mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener((LinearLayoutManager) mLayoutManager) {
      @Override
      public void onLoadMore(int current_page) {
        getMovies(current_page, API_KEY, LANGUAGE, POPULARITY_DESC);
        Toast.makeText(getApplicationContext(), "Page" + String.valueOf(current_page), Toast.LENGTH_LONG).show();
        adapterMovies.notifyDataSetChanged();
      }
    });

    getMovies(INITIAL_PAGE, API_KEY, LANGUAGE, POPULARITY_DESC);
  }

  private void getMovies(Integer page, String ... params) {

    mMovieService = ServiceGenerator.createService(MovieService.class);

    Call<MovieResults> call = mMovieService.getAllMovies(params[0], params[1], params[2], page);

    call.enqueue(new Callback<MovieResults>() {
      @Override
      public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
        if (response.isSuccessful()) {
          movies.addAll(response.body().getMovies());
          adapterMovies.notifyDataSetChanged();
        }
      }

      @Override
      public void onFailure(Call<MovieResults> call, Throwable t) {
        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
      }
    });

  }


}
