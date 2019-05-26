package pers.sfl.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.sfl.dto.QuestionDTO;
import pers.sfl.mapper.QuestionMapper;
import pers.sfl.mapper.UserMapper;
import pers.sfl.model.Question;
import pers.sfl.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-26 18:09
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;


    public List<QuestionDTO> list() {
        List<QuestionDTO> list= new ArrayList<>();
        List<Question> questionList = questionMapper.list();

        for (Question question : questionList) {
            User user = userMapper.findByID(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUsers(user);
            list.add(questionDTO);
        }
        return list;
    }
}
