package com.ren130302.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class Example {
	public static void main(String[] args) {
		String url = "https://jp.pornhub.com/webmasters/stars";
		Path json = createTempFile(Paths.get("stars.json"));
		Path output = Paths.get("pojos");

		String className = "StarsResponse";
		String packageName = "com.ren130302.webapi.pornhubapi.response";

		downloadFile(url, json);
		doPojo(className, packageName, json, output);
	}

	private static Path createTempFile(Path filePath) {
		try {
			Path tempFile = Paths.get(Files.createTempDirectory(null).toString(), filePath.toString());
			System.out.println(tempFile.toString());
			return tempFile;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void downloadFile(String url, Path target) {
		try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream())) {
			Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void doPojo(String className, String packageName, Path json, Path output) {
		JCodeModel codeModel = new JCodeModel();

		GenerationConfig config = new DGenerationConfig();
		SchemaMapper mapper = new SchemaMapper(
				new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());

		try {
			mapper.generate(codeModel, className, packageName, json.toAbsolutePath().toUri().toURL());
			codeModel.build(Files.createDirectories(output).toFile());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class DGenerationConfig
		extends DefaultGenerationConfig {
		@Override
		public SourceType getSourceType() {
			return SourceType.JSON;
		}

		@Override
		public boolean isUsePrimitives() {
			return true;
		}

		@Override
		public boolean isIncludeAdditionalProperties() {
			return false;
		}

		@Override
		public boolean isIncludeSetters() {
			return false;
		}

		@Override
		public boolean isIncludeGetters() {
			return false;
		}

		@Override
		public boolean isIncludeToString() {
			return false;
		}

		@Override
		public boolean isIncludeHashcodeAndEquals() {
			return false;
		}
	}
}
