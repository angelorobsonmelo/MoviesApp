package com.conferencia.angelorobson.recycleviewexercicio.activity.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.conferencia.angelorobson.recycleviewexercicio.R;
import com.conferencia.angelorobson.recycleviewexercicio.activity.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.conferencia.angelorobson.recycleviewexercicio.activity.constants.Constants.IMAGE_URL_BASE;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.MoviesViewHolder> {

  private List<Movie> movies;

  public AdapterMovies(List<Movie> movies) {
    this.movies = movies;
  }

  @Override
  public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemList = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.movie_card_detail, parent, false);

    return new MoviesViewHolder(itemList);
  }

  @Override
  public void onBindViewHolder(MoviesViewHolder holder, int position) {
    Movie movie = movies.get(position);

    holder.textTittle.setText(movie.getTitle());
    holder.textData.setText(movie.getReleaseDate());
    holder.textDescription.setText(movie.getDescription());

    String imgUrl = IMAGE_URL_BASE + movie.getPoster();

    Picasso.with(holder.imagePoster.getContext())
      .load(imgUrl)
    .into(holder.imagePoster);
  }

  @Override
  public int getItemCount() {
    return movies.size();
  }

  public class MoviesViewHolder extends RecyclerView.ViewHolder {
    private TextView textTittle, textData, textDescription;
    private ImageView imagePoster;

    public MoviesViewHolder(View itemView) {
      super(itemView);

      textTittle = itemView.findViewById(R.id.textTittle);
      textData = itemView.findViewById(R.id.textData);
      textDescription = itemView.findViewById(R.id.textDescription);
      imagePoster = itemView.findViewById(R.id.imagePoster);


    }
  }

}
