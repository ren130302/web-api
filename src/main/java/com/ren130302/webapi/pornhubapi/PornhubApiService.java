package com.ren130302.webapi.pornhubapi;

import java.util.Map;

import com.ren130302.webapi.lib.interfaces.IApiService;
import com.ren130302.webapi.newsapi.response.Source;

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
	@GET("/video_by_id")
	Call<Source.Response> getVideoById(@QueryMap Map<String, String> query);

	/**
	 * :param id_: id of requested video <br>
	 *
	 * @param query
	 * @return
	 */
	@GET("/is_video_active")
	Call<Source.Response> isVideoActive(@QueryMap Map<String, String> query);

	@GET("/categories")
	Call<Void> getCategories();

	@GET("/tags")
	Call<Void> getTags();

	@GET("/stars")
	Call<Void> getStarts();

	@GET("/stars_detailed")
	Call<Void> getStartsDetailed();

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
	@GET("/search")
	Call<Void> getSearch(@QueryMap Map<String, String> query);

}
