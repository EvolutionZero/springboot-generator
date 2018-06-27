package com.season.springboot.generator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Hello world!
 *
 */
public class App {

	private static final String CONTROLLER_PACKEGE = "com.example.ssm.controller";
	private static final String SERVICE_PACKEGE = "com.example.ssm.service";
	private static final String DAO_PACKEGE = "com.example.ssm.dao";
	private static final String MODEL_PACKEGE = "com.example.ssm.model";
	
	public static void main(String[] args) throws IOException {
		File material = new File(System.getProperty("user.dir") + "/material");
		File[] materials = material.listFiles();
		for (File file : materials) {
			String name = file.getName().replace(".java", "");
			String content = FileUtils.readFileToString(file ,"UTF-8");
			if(name.endsWith("Dao")) {
				addDaoAnntation(name, content);
				continue;
			}
			String idType = getIdType(content);
			String idName = getIdName(content);
			generatorService(name, idType);
			generatorServiceImpl(name, idType);
			generatorController(name, idType, idName);
		}

		System.out.println("Hello World!");
	}
	
	private static String getIdType(String content) {
		String type = "String";
		String kv = StringUtils.substringBetween(content, "private", ";").trim();
		String[] kvs = kv.split(" "); 
		return kvs.length > 0 ? kvs[0] : type;
	}
	
	private static String getIdName(String content) {
		String name = "id";
		String kv = StringUtils.substringBetween(content, "private", ";").trim();
		String[] kvs = kv.split(" "); 
		return kvs.length > 1 ? kvs[1] : name;
	}
	
	private static void generatorService(String model, String idType) {
		try {
			String service = FileUtils.readFileToString(
					new File(System.getProperty("user.dir") + "/template/TemplateService.java"), "UTF-8");
			service = service.replace("${model}", model);
			service = service.replace("${servicePackage}", SERVICE_PACKEGE);
			service = service.replace("${modelPackage}", MODEL_PACKEGE);
			service = service.replace("${idType}", idType);
			
			String targetPath = System.getProperty("user.dir") + "/generator/" + SERVICE_PACKEGE.replace(".", "/");
			FileUtils.forceMkdir(new File(targetPath));
			FileUtils.writeStringToFile(new File(targetPath + "/" + model + "Service.java"), service, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void generatorServiceImpl(String model, String idType) {
		try {
			String service = FileUtils.readFileToString(
					new File(System.getProperty("user.dir") + "/template/TemplateServiceImpl.java"), "UTF-8");
			service = service.replace("${model}", model);
			service = service.replace("${modelParam}", StringUtils.uncapitalize(model));
			service = service.replace("${daoPackage}", DAO_PACKEGE);
			service = service.replace("${servicePackage}", SERVICE_PACKEGE);
			service = service.replace("${modelPackage}", MODEL_PACKEGE);
			service = service.replace("${idType}", idType);
			
			String targetPath = System.getProperty("user.dir") + "/generator/" + SERVICE_PACKEGE.replace(".", "/") + "/impl";
			FileUtils.forceMkdir(new File(targetPath));
			FileUtils.writeStringToFile(new File(targetPath + "/" + model + "ServiceImpl.java"), service, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void generatorController(String model, String idType, String idName) {
		try {
			String controller = FileUtils.readFileToString(
					new File(System.getProperty("user.dir") + "/template/TemplateController.java"), "UTF-8");
			controller = controller.replace("${model}", model);
			controller = controller.replace("${modelParam}", StringUtils.uncapitalize(model));
			controller = controller.replace("${daoPackage}", DAO_PACKEGE);
			controller = controller.replace("${servicePackage}", SERVICE_PACKEGE);
			controller = controller.replace("${modelPackage}", MODEL_PACKEGE);
			controller = controller.replace("${controllerPackage}", CONTROLLER_PACKEGE);
			controller = controller.replace("${idType}", idType);
			controller = controller.replace("${idName}", idName);
			
			String targetPath = System.getProperty("user.dir") + "/generator/" + CONTROLLER_PACKEGE.replace(".", "/");
			FileUtils.forceMkdir(new File(targetPath));
			FileUtils.writeStringToFile(new File(targetPath + "/" + model + "Controller.java"), controller, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void addDaoAnntation(String name, String daoContent) {
		try {
			StringBuilder clazzAnnotation = new StringBuilder();
			clazzAnnotation.append("@Repository").append("\n");
			clazzAnnotation.append("@Mapper").append("\n");
			clazzAnnotation.append("public interface");
			daoContent = daoContent.replace("public interface", clazzAnnotation.toString());
			
			StringBuilder annotationImport = new StringBuilder();
			annotationImport.append("package ").append(DAO_PACKEGE).append(";\n");
			annotationImport.append("import org.apache.ibatis.annotations.Mapper;").append("\n");
			annotationImport.append("import org.springframework.stereotype.Repository;").append("\n");
			
			daoContent = daoContent.replace("package " + DAO_PACKEGE + ";", annotationImport.toString());
			
			String targetPath = System.getProperty("user.dir") + "/generator/" + DAO_PACKEGE.replace(".", "/");
			FileUtils.forceMkdir(new File(targetPath));
			FileUtils.writeStringToFile(new File(targetPath + "/" + name + ".java"), daoContent, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
