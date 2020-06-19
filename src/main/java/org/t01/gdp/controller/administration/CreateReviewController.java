package org.t01.gdp.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.t01.gdp.common.Result;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.service.CreateReviewService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CreateReviewController {
    @Autowired
    CreateReviewService createReviewService;

    //自动分配评审团队
    @PostMapping("/CreateReview")
    @ResponseBody
    public Object review_Create(List<String> subjects){
        return Result.success(createReviewService.create_review(subjects));
    }
}
