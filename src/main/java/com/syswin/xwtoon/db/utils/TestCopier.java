package com.syswin.xwtoon.db.utils;

import java.util.Date;
import java.util.List;
//
//import com.syswin.common.beancopier.ObjectConverter;
//import net.sf.cglib.beans.BeanCopier;
//import com.qitoon.framework.utils.beancopier.CachedBeanCopier;
//import org.apache.commons.beanutils.BeanUtils;
//import org.junit.Test;
//
//import com.alibaba.fastjson.JSON;
//import com.github.pagehelper.PageInfo;
//import com.google.common.collect.Lists;
//
//public class TestCopier {
//
//	  @Test
//	  public void testCopierSameType(){
//		  AccountEntity a=new AccountEntity();
//		  Date date=new Date();
//		  int i=1;
//		  a.setId(i);
//		  a.setBalance(Long.valueOf(i*10));
//		  a.setName("张顺"+i);
//		  a.setActTime(date);
//
//		  User user=new User();
//		  user.setUserId(1L);
//		  user.setName("张顺");
//		  a.setUser(user);
//		 Long start= System.currentTimeMillis();
//		 ObjectConverter<AccountDto,AccountEntity> obj=new ObjectConverter<AccountDto,AccountEntity>();
//		 AccountDto dto=obj.convert(a,AccountDto.class);
//		 System.out.println("ObjectConverter转换时间:"+(System.currentTimeMillis()-start));
//		 System.out.println(JSON.toJSONString(dto));
//
//		 Long start1= System.currentTimeMillis();
//		 BeanCopier copier = BeanCopier.create(AccountEntity.class, AccountDto.class, false);
//	     AccountDto dto1 = new AccountDto();
//	     copier.copy(a, dto1, null);
//	     System.out.println("BeanCopier转换时间:"+(System.currentTimeMillis()-start1));
//		 System.out.println(JSON.toJSONString(dto));
//
//   }
//
//	  @Test
//	  public void testCopierSameType8(){
//		  List<AccountEntity> entityList=Lists.newArrayList();
//		  Date date=new Date();
//		  for (int i = 0; i <10; i++) {
//			  AccountEntity a=new AccountEntity();
//			  a.setId(i);
//			  a.setBalance(Long.valueOf(i*10));
//			  a.setName("张顺"+i);
//			  a.setActTime(date);
//			  entityList.add(a);
//		 }
//		  PageInfo<AccountEntity> info=new PageInfo<AccountEntity>(entityList);
//		  System.out.println("entitys=>"+JSON.toJSONString(info.getList()));
//		  PageInfo<AccountDto> page=new PageInfo<AccountDto>();
//		 // CachedBeanCopier.copy(info, page);
//		  try {
//			BeanUtils.copyProperties(info, page);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		  System.out.println("info=>"+JSON.toJSONString(info));
//		  System.out.println("page=>"+JSON.toJSONString(page));
//		  List<AccountDto> dtos=page.getList();
//		  System.out.println("dtos=>"+JSON.toJSONString(dtos));
//		  System.out.println("entitys=>"+JSON.toJSONString(info.getList()));
//
//   }
//
//	  @Test
//	  public void testCopierListSameType(){
//		  List<AccountEntity> entityList=Lists.newArrayList();
//		  Date date=new Date();
//		  for (int i = 0; i <10; i++) {
//			  AccountEntity a=new AccountEntity();
//			  a.setId(i);
//			  a.setBalance(Long.valueOf(i*10));
//			  a.setName("张顺"+i);
//			  a.setActTime(date);
//			  entityList.add(a);
//		 }
//		 Long start= System.currentTimeMillis();
//		 ObjectConverter<AccountDto,AccountEntity> convertList=new ObjectConverter<AccountDto,AccountEntity>();
//		 List<AccountDto> entitydto= convertList.convertList(entityList,AccountDto.class);
//		 Long end=System.currentTimeMillis();
//		 System.out.println("ObjectConverter转换List时间:"+(System.currentTimeMillis()-start));
//		 System.out.println(JSON.toJSONString(entitydto));
//
//		 Long start1= System.currentTimeMillis();
//		 BeanCopier copier = BeanCopier.create(AccountEntity.class, AccountDto.class, false);
//		 List<AccountDto> entitydto1=Lists.newArrayList();
//		 for (AccountEntity entity : entityList) {
//			 AccountDto dto=new AccountDto();
//			 copier.copy(entity, dto, null);
//			 entitydto1.add(dto);
//		}
//	     System.out.println("BeanCopier转换时间:"+(System.currentTimeMillis()-start1));
//	     System.out.println("ObjectConverter转换List时间:"+(end-start));
//		 System.out.println(JSON.toJSONString(entitydto1));
//		 System.gc();
//    }
//
//	  @Test
//	  public void testCopierListCacheSameType(){
//		  List<AccountEntity> entityList=Lists.newArrayList();
//		  Date date=new Date();
//		  for (int i = 0; i <1000; i++) {
//			  AccountEntity a=new AccountEntity();
//			  a.setId(i);
//			  a.setBalance(Long.valueOf(i*10));
//			  a.setName("张顺"+i);
//			  a.setActTime(date);
//			  entityList.add(a);
//		 }
//		 Long start= System.currentTimeMillis();
//		 ObjectConverter<AccountDto,AccountEntity> convertList=new ObjectConverter<AccountDto,AccountEntity>();
//		 List<AccountDto> entitydto= convertList.convertList(entityList,AccountDto.class);
//		 Long end=System.currentTimeMillis();
//		 System.out.println("ObjectConverter转换List时间:"+(System.currentTimeMillis()-start));
//		 System.out.println("ObjectConverter转换:"+JSON.toJSONString(entitydto));
//
//		 Long start1= System.currentTimeMillis();
////		 BeanCopier copier = BeanCopier.create(AccountEntity.class, AccountDto.class, false);
//		 List<AccountDto> entitydto1=Lists.newArrayList();
//		 for (AccountEntity entity : entityList) {
//			 AccountDto dto=new AccountDto();
//			 CachedBeanCopier.copy(entity, dto);
//			// CachedBeanCopier.copyAndCoverter(entity, dto);
//			 entitydto1.add(dto);
//		 }
//	     System.out.println("BeanCopier转换时间:"+(System.currentTimeMillis()-start1));
//	     System.out.println("ObjectConverter转换List时间:"+(end-start));
//		 System.out.println("BeanCopier转换:"+JSON.toJSONString(entitydto1));
//    }
//
//	  @Test
//	  public void testCopierListCache2SameType(){
//		  List<AccountEntity> entityList=Lists.newArrayList();
//		  Date date=new Date();
//		  for (int i = 0; i <1000; i++) {
//			  AccountEntity a=new AccountEntity();
//			  a.setId(i);
//			  a.setBalance(Long.valueOf(i*10));
//			  a.setName("张顺"+i);
//			  a.setActTime(date);
//			  entityList.add(a);
//		 }
//		 Long start= System.currentTimeMillis();
//		 ObjectConverter<AccountDto,AccountEntity> convertList=new ObjectConverter<AccountDto,AccountEntity>();
//		 List<AccountDto> entitydto= convertList.convertList(entityList,AccountDto.class);
//		 Long end=System.currentTimeMillis();
//
//		 Long start1= System.currentTimeMillis();
////		 BeanCopier copier = BeanCopier.create(AccountEntity.class, AccountDto.class, false);
//		 List<AccountDto> dtoList=Lists.newArrayList();
//		 CachedBeanCopier.copyList(entityList, dtoList,AccountDto.class);
//		 Long end1=System.currentTimeMillis();
//
//
//		 Long start2= System.currentTimeMillis();
//		 List<AccountDto> dtoList2=CachedBeanCopier.copyList(entityList,AccountDto.class);
//		 System.out.println("BeanCopier转换时间:"+(end1-start1));
//	     System.out.println("ObjectConverter转换List时间:"+(end-start));
//		 //System.out.println(JSON.toJSONString(dtoList));
//		 System.out.println("BeanCopier2转换时间:"+(System.currentTimeMillis()-start1));
//		 //System.out.println(JSON.toJSONString(dtoList2));
//    }/}
