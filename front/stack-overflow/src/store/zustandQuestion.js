import create from "zustand";
import axios from "axios";

export const QuestionStore = create((set) => ({
  title: "",
  content: "",
  tags: [],
  setTitle: (data) => set({ title: data }),
  setContent: (data) => set({ content: data }),
  setTags: (data) => set({ tags: data }),
  postQuestion: async (url, id, dataObj) => {
    await axios
      .post(`${url}/${id}`, {
        title: dataObj.title,
        content: dataObj.content,
        tags: dataObj.tags,
      })
      .then((response) => {
        console.log(response);
      })
      .catch((err) => {
        console.log(err.message);
      });
  },
}));
