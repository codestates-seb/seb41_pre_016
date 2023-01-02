import create from "zustand";
import axios from "axios";
import produce from "immer";
const useStore = create((set, get) => ({
  questions: null,
  pageInfo: null,
  sortType: "date",
  isLoading: false,
  getQuestions: (sortType, page) => {
    set((state) => ({ isLoading: true }));
    return axios
      .get(`/question/${sortType}/`, {
        params: {
          page: page,
          size: 10,
        },
      })
      .then((res) => {
        console.log(res.data);
        set((state) => ({
          questions: res.data.questions,
          pageInfo: res.data.pageInfo,
          sortType: `${sortType}`,
          isLoading: false,
        }));
      })
      .catch((err) => {
        set((state) => ({ isLoading: false }));
        console.log(err);
      });
  },
}));

export default useStore;
