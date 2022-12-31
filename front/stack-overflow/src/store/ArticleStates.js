import create from "zustand";
import axios from "axios";

const useStore = create((set, get) => ({
  questionId: null,
  title: null,
  content: null,
  votes: null,
  views: null,
  answer_count: null,
  userId: null,
  name: null,
  createdAt: null,
  modifiedAt: null,
  answers: null,
  isLoading: false,
  getArticle: async (questionId) => {
    try {
      set((state) => ({
        isLoading: true,
      }));
      const res = await axios.get(`/question/${questionId}`);
      console.log(res.data);
      set((state) => ({
        ...res.data,
        isLoading: false,
      }));
    } catch (err) {
      set((state) => ({
        isLoading: false,
      }));
      console.log(err);
    }
  },
  voteQuestion: async (voteType, questionId) => {
    try {
      const res = await axios({
        method: "patch",
        url: `/question/${questionId}/${voteType}`,
      });
    } catch (err) {
      console.log(`voteQuestionsError: ${err}`);
    }
  },
}));

export default useStore;
