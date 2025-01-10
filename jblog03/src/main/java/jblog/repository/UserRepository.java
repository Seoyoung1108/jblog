package jblog.repository;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jblog.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private DataSource dataSource;
	private SqlSession sqlSession;

	public UserRepository(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}

	public UserVo findByIdAndPassword(String id, String password) {
		return sqlSession.selectOne("user.findByIdAndPassword", Map.of("id",id,"password",password));
	}

	public int createBlog(String blogId) {
		return sqlSession.insert("blog.insert", Map.of("picturePath","/assets/upload-images/default.jpeg","blogId",blogId));	
	}

	public int createBasicCategory(String blogId) {
		return sqlSession.insert("category.insert", blogId);
		
	}

}
