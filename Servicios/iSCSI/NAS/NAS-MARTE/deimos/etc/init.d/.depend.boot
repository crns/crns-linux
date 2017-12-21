TARGETS = mountkernfs.sh hostname.sh udev keyboard-setup mountdevsubfs.sh mdadm-raid hwclock.sh lvm2 hdparm console-setup mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh networking rpcbind nfs-common urandom checkroot.sh checkfs.sh procps checkroot-bootclean.sh bootmisc.sh udev-finish kmod kbd
INTERACTIVE = udev keyboard-setup console-setup checkroot.sh checkfs.sh kbd
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
mdadm-raid: mountkernfs.sh hostname.sh udev
hwclock.sh: mountdevsubfs.sh
lvm2: mountdevsubfs.sh udev mdadm-raid
hdparm: mountdevsubfs.sh udev
console-setup: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh kbd
mountall.sh: mdadm-raid lvm2 checkfs.sh checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
mountnfs.sh: mountall.sh mountall-bootclean.sh networking rpcbind nfs-common
mountnfs-bootclean.sh: mountall.sh mountall-bootclean.sh mountnfs.sh
networking: mountkernfs.sh mountall.sh mountall-bootclean.sh urandom procps
rpcbind: networking mountall.sh mountall-bootclean.sh
nfs-common: rpcbind hwclock.sh
urandom: mountall.sh mountall-bootclean.sh hwclock.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh hdparm
checkfs.sh: mdadm-raid lvm2 checkroot.sh
procps: mountkernfs.sh mountall.sh mountall-bootclean.sh udev
checkroot-bootclean.sh: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountnfs-bootclean.sh mountall-bootclean.sh mountall.sh mountnfs.sh udev
udev-finish: udev mountall.sh mountall-bootclean.sh
kmod: checkroot.sh
kbd: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
