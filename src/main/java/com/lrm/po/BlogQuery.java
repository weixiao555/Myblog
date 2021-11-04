package com.lrm.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title: BlogQuery
 * @projectName: blog
 * @description: null
 * @author: zhang·chuan
 * @date: 2021/10/16 17:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogQuery {

	private String title;
	private Long typeId;
	private boolean recommend;
}
