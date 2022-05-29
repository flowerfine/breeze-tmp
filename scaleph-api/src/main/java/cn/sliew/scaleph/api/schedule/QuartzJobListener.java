package cn.sliew.scaleph.api.schedule;

import java.util.Date;

import cn.hutool.core.util.StrUtil;
import cn.sliew.scaleph.common.constant.Constants;
import cn.sliew.scaleph.common.enums.TaskResultEnum;
import cn.sliew.scaleph.log.service.LogScheduleService;
import cn.sliew.scaleph.log.service.dto.LogScheduleDTO;
import cn.sliew.scaleph.service.util.SpringApplicationContextUtil;
import cn.sliew.scaleph.system.service.vo.DictVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;

/**
 * @author gleiyu
 */
@Slf4j
public class QuartzJobListener implements JobListener {
    @Override
    public String getName() {
        return "quartz-job-listener";
    }

    @SneakyThrows
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        JobDataMap dataMap = jobDetail.getJobDataMap();
        LogScheduleDTO logDTO = new LogScheduleDTO();
        logDTO.setStartTime(new Date());
        logDTO.setTaskGroup(jobKey.getGroup());
        logDTO.setTaskName(jobKey.getName());
        logDTO.appendLog(StrUtil.format("job {} in group {} begin running...", jobKey.getName(),
            jobKey.getGroup()));
        dataMap.put(Constants.JOB_LOG_KEY, logDTO);
        log.debug("job {} in group {} begin running... ", jobKey.getName(), jobKey.getGroup());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        JobKey jobKey = context.getJobDetail().getKey();
        log.debug("job {} in group {} execute vetoed", jobKey.getName(), jobKey.getGroup());
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        JobDetail jobDetail = context.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        JobDataMap dataMap = jobDetail.getJobDataMap();
        LogScheduleDTO logDTO = (LogScheduleDTO) dataMap.get(Constants.JOB_LOG_KEY);
        logDTO.setEndTime(new Date());
        if (jobException == null) {
            logDTO.setResult(
                new DictVO(TaskResultEnum.SUCCESS.getCode(), TaskResultEnum.SUCCESS.getValue()));
        } else {
            logDTO.setResult(
                new DictVO(TaskResultEnum.FAILURE.getCode(), TaskResultEnum.FAILURE.getValue()));
        }
        logDTO.appendLog(StrUtil.format("job {} in group {} execute completed", jobKey.getName(),
            jobKey.getGroup()));
        LogScheduleService
            logScheduleService = SpringApplicationContextUtil.getBean(LogScheduleService.class);
        logScheduleService.insert(logDTO);
        log.debug("job {} in group {} execute completed", jobKey.getName(), jobKey.getGroup());
    }
}
