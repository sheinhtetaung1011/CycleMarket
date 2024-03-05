package cycleTest.cycleDemo.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PageableDTO implements Serializable {

    private List<?> list;
    private int page;
    private int size;
    private int numberOfElements;
    private long totalElements;
    private int totalPages;

    public PageableDTO(List<?> list, Page<?> page){
        this.list=list;
        this.page=page.getNumber();
        this.size=page.getSize();
        this.numberOfElements=page.getNumberOfElements();
        this.totalElements=page.getTotalElements();
        this.totalPages=page.getTotalPages();
    }
}
