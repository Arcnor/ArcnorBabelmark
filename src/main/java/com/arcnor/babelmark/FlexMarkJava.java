package com.arcnor.babelmark;

import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.ext.admonition.AdmonitionExtension;
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.ext.aside.AsideExtension;
import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceExtension;
import com.vladsch.flexmark.ext.escaped.character.EscapedCharacterExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.gfm.issues.GfmIssuesExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.gfm.users.GfmUsersExtension;
import com.vladsch.flexmark.ext.gitlab.GitLabExtension;
import com.vladsch.flexmark.ext.ins.InsExtension;
import com.vladsch.flexmark.ext.jekyll.front.matter.JekyllFrontMatterExtension;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
import com.vladsch.flexmark.ext.macros.MacrosExtension;
import com.vladsch.flexmark.ext.media.tags.MediaTagsExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.ext.typographic.TypographicExtension;
import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension;
import com.vladsch.flexmark.ext.xwiki.macros.MacroExtension;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.ext.youtube.embedded.YouTubeLinkExtension;
import com.vladsch.flexmark.superscript.SuperscriptExtension;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("/flexmark-java")
@Produces(MediaType.APPLICATION_JSON)
public class FlexMarkJava {
	private static final String NAME = "FlexMark-Java";

	private static Parser parser = Parser
			.builder()
			.extensions(Arrays.asList(
					AbbreviationExtension.create(),
					AdmonitionExtension.create(),
					AnchorLinkExtension.create(),
					AsideExtension.create(),
					AttributesExtension.create(),
					AutolinkExtension.create(),
					DefinitionExtension.create(),
					EmojiExtension.create(),
					EnumeratedReferenceExtension.create(),
					EscapedCharacterExtension.create(),
					FootnoteExtension.create(),
					GfmIssuesExtension.create(),
					StrikethroughExtension.create(),
					TablesExtension.create(),
					TaskListExtension.create(),
					GfmUsersExtension.create(),
					GitLabExtension.create(),
					InsExtension.create(),
					JekyllFrontMatterExtension.create(),
					JekyllTagExtension.create(),
					MacrosExtension.create(),
					MediaTagsExtension.create(),
					SuperscriptExtension.create(),
					TocExtension.create(),
					TypographicExtension.create(),
					WikiLinkExtension.create(),
					MacroExtension.create(),
					YamlFrontMatterExtension.create(),
					YouTubeLinkExtension.create()
			))
			.build();

	@ConfigProperty(name = "flexmark_java.version")
	private String version;

	@GET
	public BabelmarkResult doGet(@QueryParam("text") String text) {
		Node document = parser.parse(text);
		HtmlRenderer renderer = HtmlRenderer.builder().build();
		final String result = renderer.render(document);

		return new BabelmarkResult(NAME, version, result);
	}
}
