import create from "zustand";
import axios from "axios";

export const QuestionStore = create((set) => ({
  title: "",
  tags: [],
  setTitle: (data) => set({ title: data }),
  setTags: (data) => set({ tags: data }),
  postQuestion: async (url, id, dataObj, cookieObj) => {
    await axios
      .post(
        `${url}/${id}`,
        {
          title: dataObj.title,
          content: dataObj.content,
          tags: dataObj.tags,
        },
        {
          headers: cookieObj,
        }
      )
      .then((response) => {
        console.log(response);
        alert("글이 등록 되었습니다");
      })
      .catch((err) => {
        console.log(err.message);
        alert("에러 발생");
      });
  },
}));
