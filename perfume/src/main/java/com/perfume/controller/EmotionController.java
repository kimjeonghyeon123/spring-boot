package com.perfume.controller;

import com.perfume.service.EmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EmotionController {

    private final EmotionService emotionService;

    @GetMapping("/emotion")
    public String mainPage() {
        return "main";
    }

    @PostMapping("/emotion")
    public String emotion(@RequestParam String sentence, Model model) {
        String emotion = emotionService.searchEmotion(sentence);
        model.addAttribute("emotion", emotion);
        //return "startForm";
        return "emotion";
    }

    @PostMapping("/recommend")
    public String recommend(@RequestParam Integer rating1, @RequestParam Integer rating2, @RequestParam Integer rating3) {


        return "result";
    }
}
