package com.cynoteck.petofyOPHR.params.workingHoursParameter;

public class WorkingHoursData {
        private String isClosed;

        private String dayId;

        private String startTime;

        private String id;

        private String endTime;

        private String allDayOpen;

        public String getIsClosed ()
        {
            return isClosed;
        }

        public void setIsClosed (String isClosed)
        {
            this.isClosed = isClosed;
        }

        public String getDayId ()
        {
            return dayId;
        }

        public void setDayId (String dayId)
        {
            this.dayId = dayId;
        }

        public String getStartTime ()
        {
            return startTime;
        }

        public void setStartTime (String startTime)
        {
            this.startTime = startTime;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getEndTime ()
        {
            return endTime;
        }

        public void setEndTime (String endTime)
        {
            this.endTime = endTime;
        }

        public String getAllDayOpen ()
        {
            return allDayOpen;
        }

        public void setAllDayOpen (String allDayOpen)
        {
            this.allDayOpen = allDayOpen;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [isClosed = "+isClosed+", dayId = "+dayId+", startTime = "+startTime+", id = "+id+", endTime = "+endTime+", allDayOpen = "+allDayOpen+"]";
        }
    }

