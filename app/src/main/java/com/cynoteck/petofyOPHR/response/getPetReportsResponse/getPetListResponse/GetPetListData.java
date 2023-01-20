package com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetListResponse;

import com.cynoteck.petofyOPHR.response.getPetReportsResponse.PagingHeader;

import java.util.ArrayList;

public class GetPetListData {

        private PagingHeader pagingHeader;

        private ArrayList<PetList> petList;

        public PagingHeader getPagingHeader ()
        {
            return pagingHeader;
        }

        public void setPagingHeader (PagingHeader pagingHeader)
        {
            this.pagingHeader = pagingHeader;
        }

        public ArrayList<PetList> getPetList ()
        {
            return petList;
        }

        public void setPetList (ArrayList<PetList> petList)
        {
            this.petList = petList;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [pagingHeader = "+pagingHeader+", petList = "+petList+"]";
        }
    }
