package jp.co.gsp.google_api.response;

/**
 * Google検索APIレスポンス
 * @author takuma.yasuda
 *
 */
public class CustomSearchAPIResponse {
	// タイトル
	private String title;
	// HTMLタイトル
	private String htmlTitle;
	// リンクURL
	private String link;
	// スニペット
	private String snippet;
	// フォーマットURL
	private String formattedUrl;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHtmlTitle() {
		return htmlTitle;
	}
	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public String getFormattedUrl() {
		return formattedUrl;
	}
	public void setFormattedUrl(String formattedUrl) {
		this.formattedUrl = formattedUrl;
	}

}
