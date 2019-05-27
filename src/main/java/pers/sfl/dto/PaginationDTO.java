package pers.sfl.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页的数据传输
 *
 * @author Scott  fl_6145@163.com
 * @create 2019-05-27 11:33
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;
        for(int i=page-3;i<page;i++){
            if(i<=0){
                continue;
            }else{
                pages.add(i);
            }
        }
        for(int i=page;i<page+4;i++){
            if(i==totalPage){
                pages.add(i);
                break;
            }
            pages.add(i);

        }

        // 是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        // 是否展示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        // 是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        // 是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }

    }
}
