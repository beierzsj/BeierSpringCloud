package com.beier.repository;

import com.beier.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository的dao实现
 *             （来源于 Spring DataJPA框架，具体查看 https://www.imooc.com/video/14552）
 *              JpaRepository继承PagingAndSortingRepository，
 *              PagingAndSortingRepository又继承CrudRepository，
 *              也就是说我们平时自定义的接口只要继承JpaRepository，就相当于拥有了增删查改，分页，等等功能。
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
