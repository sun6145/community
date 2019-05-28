package pers.sfl.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.sfl.dto.PaginationDTO;
import pers.sfl.dto.QuestionDTO;
import pers.sfl.mapper.QuestionMapper;
import pers.sfl.mapper.UserMapper;
import pers.sfl.model.Question;
import pers.sfl.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Scott fl_6145@163.com
 * @create 2019-05-26 18:09
 */
@Service
public class QuestionService {
  @Autowired private UserMapper userMapper;

  @Autowired private QuestionMapper questionMapper;

  public PaginationDTO list(Integer page, Integer size) {
    Integer totalPage = questionMapper.getCount() / size + 1;
    if (page > totalPage || page < 1) {
      page = 1;
    }
    Integer offset = (page - 1) * size;
    List<Question> questionList = questionMapper.list(offset, size);
    PaginationDTO paginationDTO = getPaginationDTO(page, totalPage, questionList);
    return paginationDTO;
  }

  public PaginationDTO list(int id, Integer page, Integer size) {

    Integer offset = (page - 1) * size;
    List<Question> questionList = questionMapper.listByUser(id, offset, size);
    int count = questionList.size();
    if (count == 0) {
      return null;
    }
    Integer totalPage = count / size + 1;
    if (page > totalPage || page < 1) {
      page = 1;
    }
    PaginationDTO paginationDTO = getPaginationDTO(page, totalPage, questionList);
    return paginationDTO;
  }

  private PaginationDTO getPaginationDTO(
      Integer page, Integer totalPage, List<Question> questionList) {
    List<QuestionDTO> questionDTOList = new ArrayList<>();
    return getPaginationDTO(page, totalPage, questionList, questionDTOList);
  }

  private PaginationDTO getPaginationDTO(
      Integer page,
      Integer totalPage,
      List<Question> questionList,
      List<QuestionDTO> questionDTOList) {
    PaginationDTO paginationDTO = new PaginationDTO();
    for (Question question : questionList) {
      User user = userMapper.findByID(question.getCreator());
      QuestionDTO questionDTO = new QuestionDTO();
      BeanUtils.copyProperties(question, questionDTO);
      questionDTO.setUsers(user);
      questionDTOList.add(questionDTO);
    }
    paginationDTO.setQuestionDTOList(questionDTOList);
    paginationDTO.setTotalPage(totalPage);
    paginationDTO.setPage(page);
    paginationDTO.setPagination(totalPage, page);
    return paginationDTO;
  }

  public QuestionDTO getQuestionById(Integer id) {
    QuestionDTO questionDTO = new QuestionDTO();
    List<Question> questionById = questionMapper.getQuestionById(id);
    for (Question question : questionById) {
      User user = userMapper.findByID(question.getCreator());
      questionDTO = new QuestionDTO();
      BeanUtils.copyProperties(question, questionDTO);
      questionDTO.setUsers(user);
    }
    return questionDTO;
  }

  public void createOrUpdate(Question question, Integer id) {
    if (id == 0) {
      question.setGmtCreate(System.currentTimeMillis());
      question.setGmtModified(question.getGmtCreate());
      question.setCommentCount(0);
      question.setViewCount(0);
      question.setLikeCount(0);
      questionMapper.create(question);
    } else {
      question.setGmtModified(System.currentTimeMillis());
      questionMapper.updateInfo(question);
    }
  }
}
