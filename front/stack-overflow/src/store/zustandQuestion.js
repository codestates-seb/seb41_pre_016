import create from "zustand";
import axios from "axios";

export const QuestionStore = create((set) => ({
  title: "",
  tags: [],
  setTitle: (data) => set({ title: data }),
  setTags: (data) => set({ tags: data }),
  postQuestion: async (url, id, dataObj,cookieObj) => {
    await axios
      .post(`${url}/${id}`, {
        title: dataObj.title,
        content: dataObj.content,
        tags: dataObj.tags,
      },{
          headers:cookieObj
      })
      .then((response) => {
        console.log(response);
      })
      .catch((err) => {
        console.log(err.message);
      });
  },
}));
