package com.conferencia.angelorobson.recycleviewexercicio.activity.model;

import android.database.DatabaseErrorHandler;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Movie {

  private String title;

  private Integer id;

  @SerializedName("backdrop_path")
  private String poster;

  @SerializedName("overview")
  private String description;

  @SerializedName("release_date")
  private String releaseDate;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }
}
