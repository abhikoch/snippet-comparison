package com.comparison.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.util.ResourceUtils;

import com.comparison.model.ObjectModel;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

public class Utils {

	public static ObjectModel getObjectFromFile(File file) {
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		try {
			return objectMapper.readValue(file, ObjectModel.class);
		} catch (Exception e) {
			new RuntimeException("Error getting Object from file");
		}
		return null;
	}

	public static String getJsonFromObject(Object testModel) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		try {
			return objectMapper.writeValueAsString(testModel);
		} catch (Exception e) {
			new RuntimeException("Error finding JSON from Object");
		}
		return null;
	}

	public static String getDifferenceResult(MapDifference<String, Object> mapDifference, Boolean iskebabFormat) {
		Map<String, Object> deltaMap = new HashMap<>();
		mapDifference.entriesDiffering().entrySet().stream().forEach(differenceItem -> {
			deltaMap.put(differenceItem.getKey(), differenceItem.getValue().rightValue());
		});
		deltaMap.putAll(mapDifference.entriesOnlyOnRight());

		return convertMap(deltaMap, iskebabFormat);
	}

	public static String convertMap(Map<String, Object> map, Boolean inKebabFormat) {
		JSONObject json = new JSONObject(map);

		YAMLFactory yamlFactory = new YAMLFactory().disable(Feature.WRITE_DOC_START_MARKER)
				.enable(Feature.MINIMIZE_QUOTES);

		ObjectMapper yamlWriter = new ObjectMapper(yamlFactory);
		ObjectMapper jsonReader = new ObjectMapper();

		if (inKebabFormat) {
			try {
				Object obj = jsonReader.readValue(JsonUnflattener.unflatten(json.toString()), Object.class);
				return yamlWriter.writeValueAsString(obj);

			} catch (JsonProcessingException e) {
				new RuntimeException("Error converting MAP to Json");
			}

		} else {
			return JsonUnflattener.unflatten(json.toString());
		}

		return null;
	}

	public static void writeToFile(String path, String data) {
		File output = new File(path);
		FileWriter writer;
		try {
			writer = new FileWriter(output);
			writer.write(data);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			new RuntimeException("Error writing to a File");
		}
	}

	public static String getMapDiffJson(String firstJson, String secondJson, Boolean iskebabFormat) {
		Map<String, Object> firstMap = JsonFlattener.flattenAsMap(firstJson);
		Map<String, Object> secondMap = JsonFlattener.flattenAsMap(secondJson);
		MapDifference<String, Object> mapDifference = Maps.difference(firstMap, secondMap);
		return getDifferenceResult(mapDifference, iskebabFormat);
	}

	public static void compareExternalFilesFromResourcefile(String resourceFileName, String externalFilePath,
			String destinationPath, Boolean isKebab) {
		try {
			ObjectModel resourceFileModel = getObjectFromFile(ResourceUtils.getFile("classpath: " + resourceFileName));

			ObjectModel externalFilemodel = getObjectFromFile(new File(externalFilePath));

			writeToFile(destinationPath, getMapDiffJson(getJsonFromObject(resourceFileModel),
					getJsonFromObject(externalFilemodel), isKebab));

		} catch (Exception e) {
			new RuntimeException("Error in retrieving Files");
		}
	}

	public static void main(String[] args) {
		compareExternalFilesFromResourcefile("", "", "", true);
	}
}
