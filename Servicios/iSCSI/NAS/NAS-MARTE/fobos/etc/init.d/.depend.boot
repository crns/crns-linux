TARGETS = mountkernfs.sh hostname.sh udev keyboard-setup mountdevsubfs.sh mdadm-raid hwclock.sh lvm2 hdparm console-setup nfs-common rpcbind checkroot.sh mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh networking urandom checkfs.sh udev-finish bootmisc.sh kbd kmod checkroot-bootclean.sh procps
INTERACTIVE = udev keyboard-setup console-setup checkroot.sh checkfs.sh kbd
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
mdadm-raid: mountkernfs.sh hostname.sh udev
hwclock.sh: mountdevsubfs.sh
lvm2: mountdevsubfs.sh udev mdadm-raid
hdparm: mountdevsubfs.sh udev
console-setup: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh kbd
nfs-common: rpcbind hwclock.sh
rpcbind: networking mountall.sh mountall-bootclean.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh hdparm
mountall.sh: mdadm-raid lvm2 checkfs.sh checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
mountnfs.sh: mountall.sh mountall-bootclean.sh networking rpcbind nfs-common
mountnfs-bootclean.sh: mountall.sh mountall-bootclean.sh mountnfs.sh
networking: mountkernfs.sh mountall.sh mountall-bootclean.sh urandom procps
urandom: mountall.sh mountall-bootclean.sh hwclock.sh
checkfs.sh: mdadm-raid lvm2 checkroot.sh
udev-finish: udev mountall.sh mountall-bootclean.sh
bootmisc.sh: mountnfs-bootclean.sh mountall.sh mountall-bootclean.sh mountnfs.sh udev checkroot-bootclean.sh
kbd: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
kmod: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
procps: mountkernfs.sh mountall.sh mountall-bootclean.sh udev
