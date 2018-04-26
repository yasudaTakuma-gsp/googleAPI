package jp.co.gsp.google_api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;

import jp.co.gsp.google_api.response.CustomSearchAPIResponse;

/**
 * GoogleAPI 検索系APIを提供します
 *
 * @author takuma.yasuda
 *
 */
public class GoogleAPI {
	// URI
	private static final String URI = "https://www.googleapis.com/customsearch/";
	// API認証キー
	private static final String API_KEY = "AIzaSyABrLE8ACZtDJru9PBnrVFGI4EVEtGJ3BQ";
	// 検索エンジンID
	private static final String SEARCH_ENGINE = "006711314822431465313:uf9g7_hr7cu";
	// 検索結果取得数
	private static final String SEARCH_NUM = "10";
	// グローバルロケーション
	private static final String GLOBAL_LOCATION = "JP";
	// 言語
	private static final String LANGUAGE = "ja";

	/**
	 * Google検索APIを指定クエリで実行します。
	 * @param query
	 * @return 検索結果リスト
	 */
	public static List<CustomSearchAPIResponse> customSearch(String query) {
		Client c = Client.create();
		c.setConnectTimeout(3 * 1000);
		c.setReadTimeout(5 * 1000);

		String json = c.resource(URI).path("v1").queryParam("key", API_KEY) // API認証キー
				.queryParam("cx", SEARCH_ENGINE) // 検索エンジンID
				.queryParam("q", query) // 検索ワード
				.queryParam("num", SEARCH_NUM) // 検索結果数
				.queryParam("gl", GLOBAL_LOCATION) // グローバルロケーション
				.queryParam("hl", LANGUAGE) // 言語
				.accept(MediaType.APPLICATION_JSON) // application/json
				.get(String.class);

		// ｊsonをJavaオブジェクトに変換
		List<CustomSearchAPIResponse> response = parse(new Gson().fromJson(json, JsonObject.class));

		return response;
	}

	/**
	 * JsonObjectをパースしてJavaオブジェクトに変換
	 * @param jsonObject
	 * @return APIResponseList
	 */
	private static List<CustomSearchAPIResponse> parse(final JsonObject jsonObject) {
		List<CustomSearchAPIResponse> list = new ArrayList<CustomSearchAPIResponse>();
		JsonArray jsonArray = jsonObject.get("items").getAsJsonArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			CustomSearchAPIResponse row = new CustomSearchAPIResponse();
			row.setTitle(jsonArray.get(i).getAsJsonObject().get("title").toString());
			row.setHtmlTitle(jsonArray.get(i).getAsJsonObject().get("htmlTitle").toString());
			row.setLink(jsonArray.get(i).getAsJsonObject().get("link").toString());
			row.setSnippet(jsonArray.get(i).getAsJsonObject().get("snippet").toString());
			row.setFormattedUrl(jsonArray.get(i).getAsJsonObject().get("formattedUrl").toString());
			list.add(row);
		}
		return list;
	}
}