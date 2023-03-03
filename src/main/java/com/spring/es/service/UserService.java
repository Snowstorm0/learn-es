package com.spring.es.service;

import com.spring.es.model.UserEntity;
import com.spring.es.model.UserRepository;
import com.spring.es.modeles.ESUser;
import com.spring.es.modeles.ESUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author 代码的路
 * @date 2023/2/23
 */

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ESUserRepository esUserRepository;
    private final TransactionTemplate transactionTemplate;

    public UserService(UserRepository userRepository,
                       ESUserRepository esUserRepository,
                       TransactionTemplate transactionTemplate) {
        this.userRepository = userRepository;
        this.esUserRepository = esUserRepository;
        this.transactionTemplate = transactionTemplate;
    }

    public void addUser(UserEntity user) {
        final UserEntity saveESUser = transactionTemplate.execute((status) ->
                userRepository.save(user)
        );
        final ESUser esUser = new ESUser();
        assert saveESUser != null;
        BeanUtils.copyProperties(saveESUser, esUser);
        esUser.setId(saveESUser.getId() + "");
        try {
            esUserRepository.save(esUser);
        } catch (Exception e) {
            log.error(String.format("保存ES错误！%s", e.getMessage()));
        }
    }

    public List<ESUser> searchES(String keyword) {
        return esUserRepository.findByNameOrJob(keyword, keyword);
    }

    public SearchHits<ESUser> searchWhole(String keyword) {
        return esUserRepository.find(keyword);
    }

    public List<UserEntity> searchDb(String key) {
        return userRepository.findUserByJobOrName(key, key);
    }
}
