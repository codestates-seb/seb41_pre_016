import create from "zustand";
import axios from "axios";
import produce from "immer";

const useStore = create((set, get) => ({
  questions: null,
  pageInfo: {
    page: 1,
    size: 10,
    totalElements: 2,
    totalPages: 10,
  },
  getQuestionsWithDefault: (page, size) => {
    return axios
      .get("/question/date/", {
        params: {
          page: page,
          size: size,
        },
      })
      .then((res) => {
        console.log(res.data);
        set((state) => ({
          questions: res.data.questions,
          pageInfo: res.data.pageInfo,
        }));
      })
      .catch((err) => console.log(err));
  },
  getQuestionsWithNoAnswer: (page, size) => {
    return axios
      .get("/question/date/", {
        params: {
          page: page,
          size: size,
        },
      })
      .then((res) => {
        set((state) => ({
          questions: res.data.questions,
          pageInfo: res.data.pageInfo,
        }));
      })
      .catch((err) => console.log(err));
  },
}));

export default useStore;
