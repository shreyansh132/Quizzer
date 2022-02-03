package com.shreyansh.quizzer.serviceImpl;

import com.shreyansh.quizzer.entities.Question;
import com.shreyansh.quizzer.repository.CategoryRepository;
import com.shreyansh.quizzer.repository.QuizRepository;
import com.shreyansh.quizzer.services.QuizService;
import com.shreyansh.quizzer.utility.FileUploadMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FileUploadMaster fileUploadMaster;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Question> getAllQuestions() {
        return quizRepository.findAll();
    }


    @Override
    public List<Question> addQuestions(List<Question> quizQuestions) {
        return quizRepository.saveAll(quizQuestions);
    }

//    @Override
//    public RestTemplateVO getUserWithAllQuizzes() {
//        RestTemplateVO restTemplateVO = new RestTemplateVO();
//        QuizCategory quizCategory = getCategoryById(10);
//        String messageFromUser = restTemplate.getForObject("http://localhost:9003/users/message", String.class);
//        restTemplateVO.setMessage(messageFromUser);
//        restTemplateVO.setQuizCategory(quizCategory);
//        return restTemplateVO;
//    }

    public String generateUniqueIdentifier() {
        final int sizeOfCharacters = 42;
        String alphaNumericString =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(sizeOfCharacters);

        for (int i = 0; i < sizeOfCharacters; i++) {
            // generate a random number between
            // 0 to alphaNumericString variable length
            int index = (int) (alphaNumericString.length() * Math.random());
            // add Character one by one in end of sb
            sb.append(alphaNumericString.charAt(index));
        }
        return sb.toString();
    }

//    public QuizCategory addCategory(QuizCategory quizCategory) {
//        System.out.println(quizCategory.toString());
//        quizCategory.setImageUrl(fileUploadMaster.uploadFile(quizCategory.getMultipartFile()));
//        return categoryRepository.save(quizCategory);
//    }
}
