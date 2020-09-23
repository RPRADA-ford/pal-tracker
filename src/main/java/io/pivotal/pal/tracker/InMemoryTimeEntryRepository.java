package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private HashMap <Long, TimeEntry> Repository = new HashMap<> ();
    private Long id = (long) 0;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        id = id +1;
        timeEntry.setId(id);
        Repository.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return Repository.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return List.copyOf(Repository.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry timeRecord = find(id);
            if (!(timeRecord == null))
            {
                timeRecord.setProjectId(timeEntry.getProjectId());
                timeRecord.setUserId(timeEntry.getUserId());
                timeRecord.setDate(timeEntry.getDate());
                timeRecord.setHours(timeEntry.getHours());

                return create(timeRecord);
            }
            else
                return timeRecord;

    }

    @Override
    public void delete(long id) {
        Repository.remove(id);
    }
}
