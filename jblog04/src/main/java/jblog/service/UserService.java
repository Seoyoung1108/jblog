package jblog.service;

import org.springframework.stereotype.Service;

import jblog.repository.UserRepository;
import jblog.vo.UserVo;

@Service
public class UserService {
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	public void join(UserVo vo) {
		userRepository.insert(vo);
		userRepository.createBlog(vo.getId());
		userRepository.createBasicCategory(vo.getId());
	}
	
	public UserVo getUser(String id, String password) {
		UserVo vo = userRepository.findByIdAndPassword(id,password);
		return vo;
	}
	
	public UserVo getUser(String id) {
		UserVo vo = userRepository.findById(id);
		return vo;
	}
}
