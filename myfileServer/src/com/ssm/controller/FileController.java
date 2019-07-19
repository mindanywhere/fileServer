package com.ssm.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
@RequestMapping("/file")
public class FileController  {

	

	// 使用MultipartFile  
	@RequestMapping("/uploadFile")  
	public ModelAndView uploadMultipartFile(MultipartFile file) {
		// 定义返回响应JSON视图
		ModelAndView mv = new ModelAndView();
		mv.setView(new MappingJackson2JsonView()); 
		// 获取原始文件名 
		String fileName = file.getOriginalFilename();
		fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
		fileName=fileName.substring(fileName.lastIndexOf("/")+1);
		System.out.println("上传文件的文件名："+fileName);
		
		// file.getContentType();没有必须的用处
		// 目标文件
		File dest = new File(fileName);
		try {
			// 保存文件，文件名保持原始的名字
			file.transferTo(dest);
			mv.addObject("success", true);
			mv.addObject("msg", "上传文件成功");
		} catch (IllegalStateException | IOException e) {
			mv.addObject("success", false);
			mv.addObject("msg", "上传文件失败");
			e.printStackTrace();
		}
		return mv;
	}

	

	
}