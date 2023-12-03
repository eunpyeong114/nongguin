package com.nongguin.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongguin.model.dao.FriendDao;
import com.nongguin.model.dao.UserDao;
import com.nongguin.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	
	@Autowired
	FriendDao friendDao;
	
	@Override
	public int signUp(User user) {
		// 이메일이 있는지 체크
		User dbUser = signIn(user);
		int result = 0;
		// 이메일이 있다면
		if(dbUser!=null) {
			// 삭제된 경우
			if(dbUser.isUserIsDeleted()) {
				result = userDao.signUp(user);
				// 삭제안된 경우(중복)
			} else {
				return -1;
			}
		} else {
			result = userDao.signUp(user);
		}
		if (result > 0)
			return 1;
		return 0;
	}

	@Override
	public User signIn(User user) {
		return userDao.signIn(user);
	}

	@Override
	public boolean deleteUser(int userId) {
		friendDao.deleteFriendBySignout(userId);
		int result = userDao.deleteUser(userId);
		System.out.println("result"+result);
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public User getUserByUserId(int userId) {
		return userDao.getUserByUserId(userId);
	}

	@Override
	public boolean modifyUser(User user) {
		int result = userDao.modifyUser(user);
		System.out.println(result);
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		return userDao.getUserByEmail(userEmail);
	}

}
