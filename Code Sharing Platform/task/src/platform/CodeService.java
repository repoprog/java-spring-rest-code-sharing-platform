package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class CodeService {

    @Autowired
    private CodeRepository codeRepository;

    public void saveCode(Code code) {

        if (code.getTime() == 0 && code.getViews() == 0) {
            code.setRestricted("none");
        } else if (code.getTime() == 0) {
            code.setRestricted("view");
        } else if (code.getViews() == 0) {
            code.setRestricted("time");
        } else {
            code.setRestricted("both");
        }
        codeRepository.save(code);
    }

    // method is searching code by unique random generated
    // uuid String in @PostMapping
    public Code findCodeByUUID(String uuid) {

        return codeRepository.findByUuid(uuid)
                .orElseThrow(() -> new CodeNotFoundException("id:" + uuid)
                );
    }

    public Code checkRestrictions(Code seenCode) {
        //if code views or time in database == 0 there are no restrictions
        if (seenCode.getViews() > 0) {
            int viewsRemaining = seenCode.getViews() - 1;
            seenCode.setViews(viewsRemaining);
            if (viewsRemaining > 0) {
                codeRepository.save(seenCode);
            } else {
                codeRepository.delete(seenCode);
            }
        }
        if (seenCode.getTime() > 0) {
            long timeRemaining = calculateTimeRemaining(seenCode);
            if (timeRemaining > 0) {
                seenCode.setTime(timeRemaining);
            } else {
                codeRepository.delete(seenCode);
                throw new CodeNotFoundException("id " + seenCode.getId());
            }
        }
        return seenCode;
    }

    public Long calculateTimeRemaining(Code seenCode) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSSSSSSSS");
        LocalDateTime loadDate = LocalDateTime.parse(seenCode.getDate(), formatter);
        LocalDateTime endDate = loadDate.plusSeconds(seenCode.getTime());

        return Duration.between(LocalTime.now(), endDate).toSeconds();
    }

    // method is searching latest 10 codes by id LONG
    public List<Code> findLatest10CodesSorted() {
        List<Code> repoCodeList = codeRepository.findAll();

        return repoCodeList.stream()
                .filter(code -> code.getTime() == 0 && code.getViews() == 0)
                .sorted(Comparator.comparing(Code::getDate).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

}
