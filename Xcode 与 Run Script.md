# Xcode 的 Run Script

[TOC]

## 常用脚本

### Build Version 自增

```bash
bundleVersion=$(/usr/libexec/PlistBuddy -c "Print CFBundleVersion" "$INFOPLIST_FILE")
bundleVersion=$((${bundleVersion} + 1))
/usr/libexec/PlistBuddy -c "Set :CFBundleVersion ${bundleVersion}" "${INFOPLIST_FILE}"
```

> 以上脚本仅适用于 CFBundleVersion 为整数的情况

