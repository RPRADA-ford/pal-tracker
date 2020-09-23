package io.pivotal.pal.tracker;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TimeEntryController {

    private TimeEntryRepository repository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repository = timeEntryRepository;

    }

    public ResponseEntity<TimeEntry>  create(TimeEntry timeEntryToCreate) {
        TimeEntry createTimeEntry = repository.create(timeEntryToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTimeEntry);
    }

    public ResponseEntity<TimeEntry> read(long timeEntryId) {
        TimeEntry foundTimeEntry = repository.find(timeEntryId);
        if (!(foundTimeEntry == null))
            return ResponseEntity.status(HttpStatus.OK).body(foundTimeEntry);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> listTimeEntry = repository.list();
        return ResponseEntity.status(HttpStatus.OK).body(listTimeEntry);
    }

    public ResponseEntity<TimeEntry> update(long timeEntryId, TimeEntry expected) {
        TimeEntry updatedTimeEntry = repository.update(timeEntryId, expected);
        if (!(updatedTimeEntry == null))
            return ResponseEntity.status(HttpStatus.OK).body(updatedTimeEntry);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedTimeEntry);

    }

    public ResponseEntity delete(long timeEntryId) {
        repository.delete(timeEntryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
