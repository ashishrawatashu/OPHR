package com.cynoteck.petofyOPHR.response.getPetReportsResponse;

    public class PagingHeader
    {
        private String totalItems;

        private String pageNumber;

        private String previousPage;

        private String hasNextPage;

        private String nextPage;

        private String totalPages;

        private String pageSize;

        private String hasPreviousPage;

        public String getTotalItems ()
        {
            return totalItems;
        }

        public void setTotalItems (String totalItems)
        {
            this.totalItems = totalItems;
        }

        public String getPageNumber ()
        {
            return pageNumber;
        }

        public void setPageNumber (String pageNumber)
        {
            this.pageNumber = pageNumber;
        }

        public String getPreviousPage ()
        {
            return previousPage;
        }

        public void setPreviousPage (String previousPage)
        {
            this.previousPage = previousPage;
        }

        public String getHasNextPage ()
        {
            return hasNextPage;
        }

        public void setHasNextPage (String hasNextPage)
        {
            this.hasNextPage = hasNextPage;
        }

        public String getNextPage ()
        {
            return nextPage;
        }

        public void setNextPage (String nextPage)
        {
            this.nextPage = nextPage;
        }

        public String getTotalPages ()
        {
            return totalPages;
        }

        public void setTotalPages (String totalPages)
        {
            this.totalPages = totalPages;
        }

        public String getPageSize ()
        {
            return pageSize;
        }

        public void setPageSize (String pageSize)
        {
            this.pageSize = pageSize;
        }

        public String getHasPreviousPage ()
        {
            return hasPreviousPage;
        }

        public void setHasPreviousPage (String hasPreviousPage)
        {
            this.hasPreviousPage = hasPreviousPage;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [totalItems = "+totalItems+", pageNumber = "+pageNumber+", previousPage = "+previousPage+", hasNextPage = "+hasNextPage+", nextPage = "+nextPage+", totalPages = "+totalPages+", pageSize = "+pageSize+", hasPreviousPage = "+hasPreviousPage+"]";
        }
    }
