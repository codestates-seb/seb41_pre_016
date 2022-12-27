import create from "zustand";

export const useAskQuestionStore = create((set) => ({
  title: "",
  content: "",
  tags: [],
  setTitle: (data) => set({ title: data }),
  setContent: (data) => set({ content: data }),
  setTags: (data) => set({ tags: data }),
}));
