package com.liu.sboot.model;

public class Mpermission {
        private  Integer id;
        private  String permissionname;
        private  String url;
        private  Role role;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPermissionname() {
            return permissionname;
        }

        public void setPermissionname(String permissionname) {
            this.permissionname = permissionname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return "Permission{" +
                    "id=" + id +
                    ", permissionname='" + permissionname + '\'' +
                    ", url='" + url + '\'' +
                    ", role=" + role +
                    '}';
        }
}
