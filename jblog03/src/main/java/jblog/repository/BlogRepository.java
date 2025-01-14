package jblog.repository;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Repository
public class BlogRepository {
	@Autowired
	private DataSource dataSource;
	private SqlSession sqlSession;
	
	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	public BlogVo findById(String id) {
		return sqlSession.selectOne("blog.findById", id);
	}
	
	public int insert(CategoryVo vo) {
		return sqlSession.insert("category.insert", vo);	
	}

	public List<CategoryVo> findAll(String id) {
		return sqlSession.selectList("category.findAll", id);
	}

	public int deleteByIdAndCategoryId(String id, Long categoryId) {
		return sqlSession.delete("category.deleteByIdAndCategoryId", Map.of("id",id,"categoryId",categoryId));
	}

	public int insertPost(PostVo vo) {
		return sqlSession.insert("post.insert", vo);	
	}

	public List<PostVo> findAllPosts(Long categoryId) {
		return sqlSession.selectList("post.findAll", categoryId);
	}

	public PostVo findByCategoryIdAndPostId(Long categoryId, Long postId) {
		return sqlSession.selectOne("post.findByCategoryIdAndPostId", Map.of("categoryId",categoryId,"postId",postId));
	}

	public int updateBlog(BlogVo blogVo) {
		return sqlSession.update("blog.update", blogVo);
	}

	public int deleteByCategoryId(Long categoryId) {
		return sqlSession.delete("post.deleteByCategoryId", categoryId);
	}
}
