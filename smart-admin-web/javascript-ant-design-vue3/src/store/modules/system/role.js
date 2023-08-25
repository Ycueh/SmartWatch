/*
 * role
 *
 *
 */
import _ from 'lodash';
import { defineStore } from 'pinia';

export const useRoleStore = defineStore({
  id: 'role',
  state: () => ({
    checkedData: [],
    treeMap: new Map(),
  }),

  actions: {
    // Initialize selected data for permission tree
    initCheckedData(data) {
      this.checkedData = [...new Set(data)];
    },
    // Select
    addCheckedData(data) {
      if (this.checkedData.some((e) => e == data)) {
        return;
      }
      this.checkedData.push(data);
    },
    // Select this level and its children
    addCheckedDataAndChildren(data) {
      let findIndex = this.checkedData.findIndex((val) => val == data.menuId);
      if (data.menuId && findIndex == -1) {
        this.addCheckedData(data.menuId);
      }
      if (data.children) {
        data.children.forEach((item) => {
          this.addCheckedDataAndChildren(item);
        });
      }
    },
    // Deselect
    deleteCheckedData(index) {
      this.checkedData.splice(index, 1);
    },
    // Deselect this level and its children
    deleteCheckedDataAndChildren(data) {
      let findIndex = this.checkedData.findIndex((val) => val == data.menuId);
      if (findIndex != -1) {
        this.deleteCheckedData(findIndex);
      }
      if (data.children) {
        data.children.forEach((item) => {
          this.deleteCheckedDataAndChildren(item);
        });
      }
    },
    // Initialize permission tree object
    initTreeMap(tree) {
      for (let treeElement of tree) {
        if (!treeElement.menuId) {
          continue;
        }
        this.treeMap.set(treeElement.menuId, treeElement);
        if (treeElement.children && !_.isEmpty(treeElement.children)) {
          this.initTreeMap(treeElement.children);
        }
      }
    },
    // Select the upper level
    selectUpperLevel(module) {
      // Get the parent key
      let parentId = module.parentId;
      if (!parentId) {
        return;
      }
      // Get the parent object from the permission tree object
      let parentModule = this.treeMap.get(parentId);
      if (!parentModule) {
        return;
      }
      // Select the parent
      let parentIndex = this.checkedData.findIndex((e) => parentModule.menuId === e);
      if (parentModule.menuId && parentIndex == -1) {
        this.addCheckedData(parentModule.menuId);
      }
      // If the parent has a parent, then recursively select upper levels
      if (parentModule.parentId) {
        this.selectUpperLevel(parentModule);
      }
    },
  },
});
