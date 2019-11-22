package com.arcnor.babelmark;

public class BabelmarkResult {
	private final String name;
	private final String version;
	private final String html;

	BabelmarkResult(String name, String version, String html) {
		this.name = name;
		this.version = version;
		this.html = html;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public String getHtml() {
		return html;
	}
}
