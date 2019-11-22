package com.arcnor.babelmark;

import org.commonmark.ext.autolink.AutolinkExtension;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.ext.ins.InsExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("/commonmark-java")
@Produces(MediaType.APPLICATION_JSON)
public class CommonMarkJava {
	private static final String NAME = "CommonMark-Java";

	private static Parser parser = Parser
			.builder()
			.extensions(Arrays.asList(
					TablesExtension.create(),
					StrikethroughExtension.create(),
					AutolinkExtension.create(),
					InsExtension.create(),
					YamlFrontMatterExtension.create(),
					HeadingAnchorExtension.create()
			))
			.build();

	@ConfigProperty(name = "commonmark_java.version")
	private String version;

	@GET
	public BabelmarkResult doGet(@QueryParam("text") String text) {
		Node document = parser.parse(text);
		HtmlRenderer renderer = HtmlRenderer.builder().build();
		final String result = renderer.render(document);

		return new BabelmarkResult(NAME, version, result);
	}
}
