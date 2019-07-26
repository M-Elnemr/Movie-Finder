
package com.example.getmovies.model.responses;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AllMoviesResponse {

    @SerializedName("dates")
    private Dates mDates;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<Result> mResults;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Dates getDates() {
        return mDates;
    }

    public void setDates(Dates dates) {
        mDates = dates;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

    public class Dates {

        @SerializedName("maximum")
        private String mMaximum;
        @SerializedName("minimum")
        private String mMinimum;

        public String getMaximum() {
            return mMaximum;
        }

        public void setMaximum(String maximum) {
            mMaximum = maximum;
        }

        public String getMinimum() {
            return mMinimum;
        }

        public void setMinimum(String minimum) {
            mMinimum = minimum;
        }

    }

}
