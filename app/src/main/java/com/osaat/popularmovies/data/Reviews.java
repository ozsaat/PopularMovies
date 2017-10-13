package com.osaat.popularmovies.data;


import android.os.Parcel;
import android.os.Parcelable;

// on pevious suggestion I will use parcelable here but leave serializable where previously used
public class Reviews {


    private String id;
    private String author;
    private String url;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }
    // implements parcelable
//    private String id;
//    private String author;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getReviewBody() {
//        return reviewBody;
//    }
//
//    public void setReviewBody(String reviewBody) {
//        this.reviewBody = reviewBody;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    private String reviewBody;
//    private String url;
//
//
//    protected Reviews(Parcel in) {
//        id = in.readString();
//        author = in.readString();
//        reviewBody = in.readString();
//        url = in.readString();
//    }
//
//    public static final Creator<Reviews> CREATOR = new Creator<Reviews>() {
//        @Override
//        public Reviews createFromParcel(Parcel in) {
//            return new Reviews(in);
//        }
//
//        @Override
//        public Reviews[] newArray(int size) {
//            return new Reviews[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(id);
//        dest.writeString(author);
//        dest.writeString(reviewBody);
//        dest.writeString(url);
//    }
}
