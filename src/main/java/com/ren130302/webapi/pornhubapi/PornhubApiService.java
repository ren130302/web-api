package com.ren130302.webapi.pornhubapi;

import java.util.Map;

import com.ren130302.webapi.lib.interfaces.IApiService;
import com.ren130302.webapi.pornhubapi.response.CategoriesResponse;
import com.ren130302.webapi.pornhubapi.response.SearchResponse;
import com.ren130302.webapi.pornhubapi.response.StarsDetailedResponse;
import com.ren130302.webapi.pornhubapi.response.StarsResponse;
import com.ren130302.webapi.pornhubapi.response.TagsResponse;
import com.ren130302.webapi.pornhubapi.response.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface PornhubApiService
	extends IApiService {
	/**
	 *
	 * :param id: id of requested video <br>
	 *
	 * Optional<br>
	 * :param thumbsize:<br>
	 *
	 * @param query
	 * @returnVideo
	 */
	@GET("/webmasters/video_by_id")
	Call<VideoResponse> getVideoById(@QueryMap Map<String, String> query);

	/**
	 * :param id: id of requested video <br>
	 *
	 * @param query
	 * @return
	 */
	@GET("/webmasters/is_video_active")
	Call<VideoResponse> isVideoActive(@QueryMap Map<String, String> query);

	@GET("/webmasters/categories")
	Call<CategoriesResponse> getCategories();

	@GET("/webmasters/tags")
	Call<TagsResponse> getTags();

	@GET("/webmasters/stars")
	Call<StarsResponse> getStars();

	@GET("/webmasters/stars_detailed")
	Call<StarsDetailedResponse> getStarsDetailed();

	/**
	 * Request<br>
	 * :param q:<br>
	 * :param page:<br>
	 * :param thumbsize: Possible values are
	 * small,medium,large,small_hd,medium_hd,large_hd<br>
	 * <br>
	 * Optional<br>
	 * :param ordering: Text. Possible values are featured, newest, mostviewed and
	 * rating<br>
	 * :param category:<br>
	 * :param phrase: Array. Used as pornstars filter.<br>
	 * :param tags:<br>
	 * :param period: Text. Only works with ordering parameter. Possible values are
	 * weekly, monthly, and alltime<br>
	 *
	 * @param query
	 * @return
	 */
	@GET("/webmasters/search")
	Call<SearchResponse> getSearch(@QueryMap Map<String, String> query);

}
