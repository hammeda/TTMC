import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'io.ionic.starter',
  appName: 'ttmc-front',
  webDir: 'www',
  server: {
    url: "http://192.168.1.15:8100",
    //url: "http://172.20.10.2:8100",
    cleartext: true
  }
};

export default config;
