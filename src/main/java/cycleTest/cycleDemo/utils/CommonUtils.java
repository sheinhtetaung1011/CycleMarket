package cycleTest.cycleDemo.utils;

import cycleTest.cycleDemo.entity.BaseEntity;
import cycleTest.cycleDemo.exception.BadRequestException;
import cycleTest.cycleDemo.exception.ResourceNotFoundException;
import cycleTest.cycleDemo.payloads.ApiErrorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommonUtils {
     public static ApiErrorResponse getFieldErrors(Errors errors){
          HashMap<String,String> errorMessages=new HashMap<>();
          for(FieldError fieldError :errors.getFieldErrors()){
               errorMessages.put(fieldError.getField(),fieldError.getDefaultMessage());
          }
          return ApiErrorResponse.builder().error(true).fieldErrors(errorMessages).build();
     }

     public  static  <DTO ,ENTITY> List<DTO> getDTOList (List<ENTITY> entityList, Function<ENTITY,DTO> mapper){
          List<DTO> dtoList = entityList
                  .stream()
                  .map(mapper)
                  .collect(Collectors.toList());
          return dtoList;
     }

     public  static <E extends BaseEntity> void checkDuplicate(Optional<E> entity,Long id,String errorField){
          if(entity.isPresent()){
               if(!Objects.isNull(id)){
                 if(!(entity.get().getId()==id))
                      throw new BadRequestException(errorField+" is  duplicated!");
               }else throw new BadRequestException(errorField+" is duplicated!");
          }
     }

     public static  <E,R extends JpaRepository<E,Long>> E getById (Long id, R repository){
          E entity=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid id"));
          return entity;
     }

     public static ApiErrorResponse returnSuccessfulMessage(String message){
          return  ApiErrorResponse.builder().error(false).message(message).build();
     }

     public static void validateIdForUpdate(Long id,Errors errors){
          if(id==null)
               errors.rejectValue("id","error.name","Id is required");
     }


    public static List<String> separateString(String file, String separator) {
          String[] stringArray=file.split(separator);
          List<String> stringList= Arrays.asList(stringArray);
          return stringList;
    }

    public static Date addDateByDay(Date date,Integer day){

          Calendar calendar=Calendar.getInstance();
          calendar.setTime(date);
          calendar.add(Calendar.DATE,day);
          date=calendar.getTime();
          return date;
    }
}
