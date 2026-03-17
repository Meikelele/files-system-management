package pl.mbak.filesmanager.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mbak.filesmanager.dto.GlobalStatisticsResponse;
import pl.mbak.filesmanager.service.StatisticService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticController {


    private final StatisticService statisticService;

    @GetMapping("/global")
    public GlobalStatisticsResponse getGlobalStatistic() {
        return statisticService.getGlobalStatistic();
    }
}
