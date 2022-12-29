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
      console.log(err);
    }
  },
}));

export default useStore;
